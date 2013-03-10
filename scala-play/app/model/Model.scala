package model

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Image(id: Int, name: String, albumId: Int, score: Int) {
  def apply(score: Int) = Image(id, name, albumId, score)
}
case class Album(id: Int, name: String)

trait Result
case class ImageResult(images: List[Image]) extends Result
case class AlbumResult(albums: List[Album]) extends Result



