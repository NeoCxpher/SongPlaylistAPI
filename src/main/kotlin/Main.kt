package org.example


import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.vertx.core.Vertx
import io.vertx.core.json.jackson.DatabindCodec
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.codec.BodyCodec
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.ext.consul.aclTokenOf

//data class Book(val name: String, val author: String, val content: String = "", val numPages: Int = 0)


//// Database Layer
//class Library() {
//    private val myLibrary = mutableMapOf<String, Book>()
//
//    fun insertBook(book: Book) {
//        myLibrary[book.name] = book
//    }
//
//
//    fun deleteBook(bookName: String) {
//        myLibrary.remove(bookName)
//    }
//
//
//    fun getBookByName(bookName: String): Book? {
//        return myLibrary[bookName]
//    }
//
//
//    fun updateBook(book: Book) {
//        myLibrary[book.name] = book
//    }
//
//
//    fun getListofBooks(): MutableList<Book?> {
//        val userList: MutableList<Book?> = ArrayList()
//        for (key in myLibrary.keys) {
//            userList.add(myLibrary[key])
//        }
//        return userList
//    }
//}
//
//
//interface BookServerAPI {
////     Perform CRUD Operations
//    fun handleInsert(rtContext: RoutingContext)
//    fun handleGetBook(rtContext: RoutingContext)
//    fun getListofBooks(rtContext: RoutingContext)
//    fun handleUpdate(rtContext: RoutingContext)
//    fun handleDelete(rtContext: RoutingContext)
//}
//
//
//class BookService(private val db: Library) : BookServerAPI {
//    override fun handleInsert(rtContext: RoutingContext) {
////         Insert the song
//        val user = rtContext.body().asPojo(Book::class.java)
//        db.insertBook(user)
//        rtContext.response().end("Book inserted!")
//         println(rtContext.response().statusCode)
//    }
//
//
//    override fun handleGetBook(rtContext: RoutingContext) {
////         Get details corresponding to the user
//        val name = rtContext.pathParam("bookName")
//        val user = db.getBookByName(name)
//        rtContext.json(user)
//         println(rtContext.response().statusCode)
//    }
//
//
//    override fun getListofBooks(rtContext: RoutingContext) {
////         Get list of all available users
//        val bookList = db.getListofBooks()
//        rtContext.json(bookList)
////         Set the status codes using .setStatuscode()
//    }
//
//
//    override fun handleUpdate(rtContext: RoutingContext) {
////         Update the song
//        val book = rtContext.body().asPojo(Book::class.java)
//        db.insertBook(book)
//        rtContext.response().end("Book updated!")
//         println(rtContext.response().statusCode)
//    }
//
//
//    override fun handleDelete(rtContext: RoutingContext) {
//        val name = rtContext.pathParam("bookName")
//        db.deleteBook(name)
//        rtContext.response().end("Book deleted!")
//         println(rtContext.response().statusCode)
//    }
//
//
//}
//
//
//// Creating a web-API using vertx.Web
//// Authentication logic is a middleware
//fun main() {
////     IMPORTANT MODULE
//    DatabindCodec.mapper().registerKotlinModule()
//
//
//    val vertx = Vertx.vertx()
//    val rtr = Router.router(vertx)
//    val uServ = BookService(Library())
//
//
////     IMPORTANT MODULE
//    rtr.route().handler(BodyHandler.create())
//
//
//    rtr.put("/books").handler(uServ::handleInsert)
//    rtr.get("/books").handler(uServ::getListofBooks)
//    rtr.post("/books").handler(uServ::handleUpdate)
//
//
//    rtr.get("/books/:bookName").handler(uServ::handleGetBook)
//    rtr.delete("/books/:bookName").handler(uServ::handleDelete)
//
//
//    rtr.get("/").handler{
//        it.redirect("http://localhost:9000/books")
//    }
////     start a http server on the corresponding port
//    vertx.createHttpServer().requestHandler(rtr).listen(9000)
//    // CHANGES
//
//}
//
//
///*
//* YAML CODE
//openapi: 3.0.0
//info:
//  title: Book API
//  version: 1.0.0
//paths:
//  /books:
//    get:
//      summary: Get list of books
//      responses:
//        '200':
//          description: OK
//          content:
//            application/json:
//              schema:
//                type: array
//                items:
//                  $ref: '#/components/schemas/Book'
//    post:
//      summary: Update a book
//      requestBody:
//        required: true
//        content:
//          application/json:
//            schema:
//              $ref: '#/components/schemas/Book'
//      responses:
//        '200':
//          description: OK
//          content:
//            text/plain:
//              schema:
//                type: string
//  /books/{bookName}:
//    get:
//      summary: Get a book by name
//      parameters:
//        - name: bookName
//          in: path
//          required: true
//          schema:
//            type: string
//      responses:
//        '200':
//          description: OK
//          content:
//            application/json:
//              schema:
//                $ref: '#/components/schemas/Book'
//    delete:
//      summary: Delete a book by name
//      parameters:
//        - name: bookName
//          in: path
//          required: true
//          schema:
//            type: string
//      responses:
//        '200':
//          description: OK
//          content:
//            text/plain:
//              schema:
//                type: string
//components:
//  schemas:
//    Book:
//      type: object
//      properties:
//        name:
//          type: string
//        author:
//          type: string
//        content:
//          type: string
//        numPages:
//          type: integer
//          format: int32
//*/

// API ON SONGS PLAYLIST

data class PlaylistAttribute(val name:String, val singer: String, val duration: Float, val rating: Int)


// DATABASE
class Playlist (){
    private val myPlaylist = mutableMapOf<String, PlaylistAttribute>()
    fun add_song(song: PlaylistAttribute){
        if(!myPlaylist.containsKey(song.name)) {
            println("new song has been inserted")
        }
        else {
            println("song exists, has been updates")
        }
        myPlaylist[song.name] = song
    }

    fun delete_song (song_name: String){
        if (myPlaylist.containsKey(song_name)) {
            println("song has been deleted")
            myPlaylist.remove(song_name)
        }
        else {
            println("Song not in the playlist")
        }
    }

    fun update_song (song: PlaylistAttribute){
        if(!myPlaylist.containsKey(song.name)) {
            println("new song has been inserted")
        }
        else {
            println("song exists, has been updates")
        }
        myPlaylist[song.name] = song
    }

    fun get_all_songs():MutableMap<Int, PlaylistAttribute?>{
        val all_songs = mutableMapOf<Int, PlaylistAttribute?>()
        var ind: Int = 1
        for (keys in myPlaylist.keys) {
            all_songs[ind] = myPlaylist[keys]
            ind += 1
        }
        return all_songs
    }

    fun get_song (song_name:String): PlaylistAttribute?{
        return myPlaylist[song_name]
    }
}

class Functions(private val db: Playlist) {
    fun HandleInsert(rtContext: RoutingContext) {
        val new_song = rtContext.body().asPojo(PlaylistAttribute::class.java)
        println(new_song)
        db.add_song(new_song)
        rtContext.response().setChunked(true).end("Song Added")
    }

    fun HandleDelete(rtContext: RoutingContext) {
        val song_name = rtContext.pathParam("name")
        db.delete_song(song_name)
    }

    fun HandleUpdate(rtContext: RoutingContext) {
        val new_song = rtContext.body().asPojo(PlaylistAttribute::class.java)
        db.update_song(new_song)
        rtContext.response().setChunked(true).end("Song updated")
    }

    fun HandleAllSongs(rtContext: RoutingContext) {
        val songs = db.get_all_songs()
        rtContext.json(songs)
    }

    fun HadnleSong(rtContext: RoutingContext) {
        val name = rtContext.pathParam("name")
        val song = db.get_song(name)
        rtContext.json(song)
    }

}
fun main() {
    DatabindCodec.mapper().registerKotlinModule() // must
    val vertx = Vertx.vertx()
    val router = Router.router(vertx)
    val touse = Functions(Playlist())

    router.route().handler(BodyHandler.create()) // must

    router.put("/songs").handler(touse::HandleInsert)
    router.get("/songs").handler(touse::HandleAllSongs)
    router.post("/songs").handler(touse::HandleUpdate)
    router.delete("/songs/:name").handler(touse::HandleDelete)
    router.get("/songs/:name").handler(touse::HadnleSong)

    router.get("/").handler() {
        it.redirect("https://localhost:8080/songs")
    }

    vertx.createHttpServer().requestHandler(router).listen(8080)
}
/*
YAML for the above code


 */