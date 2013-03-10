package model

/**
 * Le modèle représente ce que fait le système (événements) et avec quelques identités
 * 
 * Listes des commandes sur le modèle :
 *  - ajouter une image dans un album ou non
 *  - supprimer une image
 *  - chercher une image
 *  - afficher toutes les images d'un album
 *  - trier les images suivant un critère (par ordre alphabétique, date...)
 */

trait Event

case class AddImage(image:Image) extends Event

case class AddAlbum(album:Album) extends Event

case class RemoveImage(withId:Int) extends Event

case class ModifyImageScore(score:Int, withId: Int) extends Event

case class FetchAllImagesFromOneAlbum(albumId:Int) extends Event

case class SortImages(criteria: Option[Criteria]) extends Event

