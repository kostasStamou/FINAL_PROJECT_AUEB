import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import { PostsListComponent } from './posts-list/posts-list.component';
import { CreatePostComponent } from './create-post/create-post.component'
import { ReactiveFormsModule } from '@angular/forms';
import { DeletePostComponent } from './delete-post/delete-post.component';

const routes: Routes = [
  {path: '', component: PostsListComponent},
  {path: 'create', component: CreatePostComponent},
  {path: 'delete', component: DeletePostComponent}
];


@NgModule({
  declarations: [
    PostsListComponent,
    CreatePostComponent,
    DeletePostComponent
  ],
  imports: [
    CommonModule, RouterModule.forChild(routes), ReactiveFormsModule
  ]
})
export class PostsModule { }
