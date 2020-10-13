import { Component, OnInit } from '@angular/core';

const navSlide = () => {
  const burger = document.querySelector('.burger');
  const nav = document.querySelector('.nav-links');
  const navLinks = document.querySelectorAll('.nav-links li');
//togle nav
  burger.addEventListener('click', () => {
    nav.classList.toggle('nav-active');

    //animate links
    navLinks.forEach((link, index) => {
      if ( (link as HTMLElement).style.animation) {
        (link as HTMLElement).style.animation = ''
      } else {
        (link as HTMLElement).style.animation = 'navLinkFade 0.5s ease forwards ${index /7 + 1.5}s';
          console.log(index/7 + 1.5)
      }
    });

    //burger anim
    burger.classList.toggle('toggle');

  });

}
@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
  animations: [ ]
})
export class NavBarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    navSlide();
  }
}
