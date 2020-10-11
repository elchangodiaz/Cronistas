import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { ArticleListComponent } from './article-list/article-list.component';
import { ParallaxSrcollComponent } from './parallax-srcoll/parallax-srcoll.component';


const routes: Routes = [
  { path: 'articles', component: ArticleListComponent}, 
  { path: 'about', component: AboutComponent},
  { path: '', component: ParallaxSrcollComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
