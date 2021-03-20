import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { ArticleListComponent } from './article-list/article-list.component';
import { ConsultasComponent } from './consultas/consultas.component';
import { ContactComponent } from './contact/contact.component';
import { InicioComponent } from './inicio/inicio.component';
import { MapForestComponent } from './map-forest/map-forest.component';
import { ParallaxSrcollComponent } from './parallax-srcoll/parallax-srcoll.component';


const routes: Routes = [
  { path: 'about', component: ArticleListComponent}, 
  { path: 'proyectos', component: AboutComponent},
  { path: '', component: ParallaxSrcollComponent},
  { path: 'cronicas', component: InicioComponent},
  { path: 'contact', component: ContactComponent},
  { path: 'consultas', component: ConsultasComponent},
  { path: 'map', component: MapForestComponent} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
