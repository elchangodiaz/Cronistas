import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  constructor() { }

  name: string;
  email: string;
  message: string;

  ngOnInit(): void {

  }

  submitForm() {
    const message = `My name is ${this.name}. My email is ${this.email}.
                    My message is ${this.message}`;
    //grab all the fields and their values
    alert(message)
  }

}