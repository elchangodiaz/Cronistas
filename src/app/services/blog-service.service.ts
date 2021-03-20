import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import { HttpClient } from '@angular/common/http';
import { Publication } from '../Model/Publication';

@Injectable({
  providedIn: 'root'
})
export class BlogServiceService {

  publication:Publication

  constructor(
    private firestore: AngularFirestore
    /*private http: HttpClient*/
  ) { }

    Url='http://localhost/8080/cronistastopilejo/api/blog';

    getPublications(){
      return this.firestore.collection("publication").snapshotChanges();
      /*return this.http.get<Publication[]>(this.Url);*/
    }

    createPublication(publication:any){
      return this.firestore.collection("publication").add(publication);
      /* return this.http.post<Publication>(this.Url + '/add', publication); */
    }

    updatePublication(id:any, publication:any){
      return this.firestore.collection("publication").doc(id).update(publication);
      /*return this.http.put('${this.Url}' + '/{id}/update', id, publication);*/
    }

    deletePublication(id:any){
      return this.firestore.collection("publication").doc(id).delete();
      /*return this.http.delete('${this.Url}' + '/{id}/delete', id);*/
    }

}
