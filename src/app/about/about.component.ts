import { Component, Injectable, OnInit, ɵwhenRendered } from '@angular/core';
import "@lottiefiles/lottie-player";

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css'],
})


export class AboutComponent implements OnInit {

  externals: [{ '@lottiefiles/lottie-player': '@lottiefiles/lottie-player' }]
  
  constructor() {
  }

  ngOnInit(): void {
    
  }


}

