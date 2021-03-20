import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { FooterComponent } from './footer/footer.component';
import { ArticleListComponent } from './article-list/article-list.component';
import { AboutComponent } from './about/about.component';
import { ParallaxSrcollComponent } from './parallax-srcoll/parallax-srcoll.component';
import { InicioComponent } from './inicio/inicio.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LottieAnimationViewModule } from 'ng-lottie';
import { ContactComponent } from './contact/contact.component';
import { FormsModule } from '@angular/forms';
import { environment } from '../environments/environment';
import { AngularFireModule } from '@angular/fire';
import { AngularFirestoreModule } from '@angular/fire/firestore';
import { ConsultasComponent } from './consultas/consultas.component';
import {NgxPaginationModule} from 'ngx-pagination';
import {ReactiveFormsModule } from '@angular/forms'
import { MapForestComponent } from './map-forest/map-forest.component';
import { BlogServiceService } from '../app/services/blog-service.service';
import { HttpClientModule } from '@angular/common/http'

@NgModule({
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  declarations: [
    AppComponent,
    NavBarComponent,
    FooterComponent,
    ArticleListComponent,
    AboutComponent,
    ParallaxSrcollComponent,
    InicioComponent,
    ContactComponent,
    ConsultasComponent,
    MapForestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    BrowserAnimationsModule,
    LottieAnimationViewModule.forRoot(),
    FormsModule,
    AngularFireModule.initializeApp(environment.firebase, 'angularfs'),
    AngularFirestoreModule,
    NgxPaginationModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [BlogServiceService],
  bootstrap: [ AppComponent]

})
export class AppModule { }
export class CustomModule { }