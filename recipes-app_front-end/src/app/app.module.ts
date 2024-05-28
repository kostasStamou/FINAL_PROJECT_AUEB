import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router'
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { from } from 'rxjs';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MenuComponent } from './menu/menu.component';
import { UserService } from './user-service.service';


const routes: Routes = [
  {path:"", loadChildren: () => import('./login-signup/login-signup.module').then((m) => m.LoginSignupModule)},
  {path:"customer", loadChildren: () => import('./customers/customers.module').then((m) => m.CustomersModule)},
  {path:"recipe", loadChildren: () => import('./recipes/recipes.module').then((m) => m.RecipesModule)},
  {path:"post", loadChildren: () => import('./posts/posts.module').then((m) => m.PostsModule)},
  {path:"**",component: PageNotFoundComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    MenuComponent,
   
  ],
  imports: [
    BrowserModule,RouterModule.forRoot(routes), HttpClientModule, ReactiveFormsModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
