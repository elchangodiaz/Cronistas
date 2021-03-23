import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import { HttpClient } from '@angular/common/http';
import { Publication } from '../Model/Publication';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BlogServiceService {

  publication:Publication

  constructor(
    private firestore: AngularFirestore,
    private httpClient: HttpClient
  ) { }

    Url='http://localhost:8080/api/blog';

    getPublications(): Observable<any> {
      /*return this.firestore.collection("publication").snapshotChanges();*/
      return this.httpClient.get(this.Url);
    }

    createPublication(publication:any) : Observable<any> {
      /*return this.firestore.collection("publication").add(publication);*/
      return this.httpClient.post(this.Url + '/add', publication); 
    }

    updatePublication(publication:any, idPublication:any,) : Observable<any>{
      /*return this.firestore.collection("publication").doc(id).update(publication);*/
      return this.httpClient.put(this.Url + '/' + idPublication + '/update', publication);
    }

    deletePublication(idPublication:any) : Observable<any>{
      /*return this.firestore.collection("publication").doc(id).delete();*/
      return this.httpClient.delete(this.Url + '/' + idPublication + '/delete');
    }

}
