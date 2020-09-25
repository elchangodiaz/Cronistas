import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-parallax-srcoll',
  templateUrl: './parallax-srcoll.component.html',
  styleUrls: ['./parallax-srcoll.component.css']
})
export class ParallaxSrcollComponent implements OnInit {
  fullImagePath: String;

  constructor() { 
    this.fullImagePath = '/assets/images/lacima.jpg'
  }

  ngOnInit(): void {
  }

}
