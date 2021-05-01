import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import { AngularFireDatabase, AngularFireList } from '@angular/fire/database';
import { HttpClient } from '@angular/common/http';
import { Publication } from '../Model/Publication';
import { Observable } from 'rxjs';
import { ImageBlog } from '../Model/ImageBlog';

@Injectable({
  providedIn: 'root'
})
export class ImageServiceService {

  imageBlog: ImageBlog;

  constructor(
    private firestore:AngularFirestore,
    private httpClient: HttpClient
  ) { }

  Url='http://localhost:8081/api/img';
  /*Url='https://consejo-cronistas-topilejo.herokuapp.com/api/blog';*/

  getImages(): Observable<any> {
    /*return this.firestore.collection("publication").snapshotChanges();*/
    return this.httpClient.get(this.Url);
  }

  uploadImage(imageBlog:any) : Observable<any> {
    /*return this.firestore.collection("publication").add(imageBlog);*/
    return this.httpClient.post(this.Url + '/add', imageBlog);
  }

  updateImage(imageBlog:any, idImage:any,) : Observable<any>{
    /*return this.firestore.collection("publication").doc(id).update(imageBlog);*/
    return this.httpClient.put(this.Url + '/' + idImage + '/update', imageBlog);
  }

  deleteImage(idImage:any) : Observable<any>{
    /*return this.firestore.collection("publication").doc(idImage).delete();*/
    return this.httpClient.delete(this.Url + '/' + idImage + '/delete');
  }


}
