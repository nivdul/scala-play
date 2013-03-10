package model

import play.api.db.DB
import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._

/**
 *  classe principale qui va gérer les différentes commandes de mon système via des l'objet Event
 */
case class System(event: Event, imageResult: ImageResult, albumResult: AlbumResult) extends App {
  /**
   * action propre à chaque Event
   */
  def applyEvent(event: Event):Result = event match {
    case AddImage(image) => addImage(image)
    case AddAlbum(album) => addAlbum(album)
    case RemoveImage(withId) => remove(withId)
    case ModifyImageScore(score, withId) => modify(score, withId)
    case FetchAllImagesFromOneAlbum(albumId) => fetchImages(albumId)
    case SortImages(criteria) => criteria match {
      case Some(criteria) => sort(criteria)
      case None => ImageResult(this.imageResult.images)
    }
  }

  def addImage(image: Image):ImageResult = ImageResult(image :: imageResult.images)
  
  def addAlbum(album: Album):AlbumResult = AlbumResult(album :: albumResult.albums)

  def remove(withId: Int):ImageResult = ImageResult(imageResult.images.filter(x => x.id == withId))

  def modify(score: Int, withId: Int):ImageResult = ImageResult(for (image <- imageResult.images if image.id == withId) yield image.apply(image.score + score))

//  def modify(score: Int, withId: Int):List[Image] = imageResult.images.filter(x => x == withId).map(x => x.apply(x.score + score)
    
  def fetchImages(albumId: Int):ImageResult = ImageResult(imageResult.images.filter(x => x.id == albumId))

  def sort(criteria: Criteria):ImageResult = criteria match {
    case ByName => ImageResult(imageResult.images.sortBy(image => image.name))
    case ById => ImageResult(imageResult.images.sortBy(image => image.id))
    case _ => ImageResult(this.imageResult.images)
  }

}