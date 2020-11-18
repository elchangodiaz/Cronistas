import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { ArticleListComponent } from './article-list/article-list.component';
import { ContactComponent } from './contact/contact.component';
import { InicioComponent } from './inicio/inicio.component';
import { ParallaxSrcollComponent } from './parallax-srcoll/parallax-srcoll.component';


const routes: Routes = [
  { path: 'about', component: ArticleListComponent}, 
  { path: 'proyectos', component: AboutComponent},
  { path: '', component: ParallaxSrcollComponent},
  { path: 'cronicas', component: InicioComponent},
  { path: 'contact', component: ContactComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
