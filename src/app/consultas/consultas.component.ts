import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { BlogServiceService } from '../services/blog-service.service';
import { Publication } from '../Model/Publication'

@Component({
  selector: 'app-consultas',
  templateUrl: './consultas.component.html',
  styleUrls: ['./consultas.component.css']
})
export class ConsultasComponent implements OnInit {

  publication : Publication[];
  publications:Publication[];
  closeResult = '';

  publicationsForm: FormGroup;
  idFirebaseActualizar: string;
  actualizar: boolean;

  constructor(
    private modalService: NgbModal,
    public fb: FormBuilder,
    private blogServiceService: BlogServiceService,
  ) { }

  config: any;
  collection = { count: 20, data: [] }

  ngOnInit(): void {

    this.idFirebaseActualizar = "";
    this.actualizar = false;
    
    this.config = {
      itemsPerPage: 5,
      currentPage: 1,
      totalItems: this.collection.data.length
    };


    this.publicationsForm = this.fb.group({
      title: ['', Validators.required],
      content: ['', Validators.required],
      image: ['', Validators.required],
      /*postDate: ['', Validators.required]*/
    })


    this.blogServiceService.getPublications().subscribe(resp => {
      this.publications = resp;  
      /*this.collection.data = resp.map((e: any) => {*/
       /* console.log(resp);*/
     /*   return {
          title: e.payload.doc.data().title,
          content: e.payload.doc.data().content,
          image: e.payload.doc.data().image,
          postDate: e.payload.doc.data().postDate,
          idFirebase: e.payload.doc.id
        }
      })*/
    },
      error => {
        console.error(error);
      });

  }


  pageChanged(event) {
    this.config.currentPage = event;
  }

  eliminar(publication): void {
    /*this.blogServiceService.deletePublication(item.idFirebase);*/
    this.blogServiceService.deletePublication(publication.idPublication).subscribe(resp => {
     /* console.log(resp)*/
      this.publications.push(resp);
      this.publications = this.publications.filter(publication => resp.idPublication!==publication.idPublication)
    })
  }

  guardarRegistro(): void {
    this.blogServiceService.createPublication(this.publicationsForm.value).subscribe(resp => {
      this.publicationsForm.reset();
      this.modalService.dismissAll();
      this.publications.push(resp);
    },
    /*this.blogServiceService.createPublication(this.publicationsForm.value).then(resp => {
      this.publicationsForm.reset();
      this.modalService.dismissAll();
    })).catch(error => {*/
    error => {
      console.error(error);
    });
  }

  actualizarRegistro(): void {
    /*if (idPublication !== null || idPublication !== "") {*/
        /*this.blogServiceService.updatePublication(this.idFirebaseActualizar, this.publicationsForm.value).then(resp => {
        this.publicationsForm.reset();
        this.modalService.dismissAll();
      }).catch(error => {
        console.error(error);
      });
      },*/
      this.blogServiceService.updatePublication(this.idFirebaseActualizar, this.publicationsForm.value).subscribe(resp => {
        this.publicationsForm.reset();
        this.modalService.dismissAll();
        this.publications = this.publications.filter(publication => resp.idPublication!==publication.idPublication)
        this.publications.push(resp);
      },
      error => {
        console.error(error);
      });
    }

  openEditar(content, item:any) {
    this.publicationsForm.setValue({
      title: item.title,
      content: item.content,
      image: item.image,
      /*postDate: item.postDate*/
    });
    this.idFirebaseActualizar = item.idPublication;
    this.actualizar = true;
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  open(content) {
    this.actualizar = false;
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

}
