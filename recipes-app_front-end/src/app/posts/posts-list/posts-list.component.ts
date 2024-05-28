import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/app.interfaces'; 

import { PostService } from 'src/app/posts/posts.service'; 

@Component({
  selector: 'app-posts-list',
  templateUrl: './posts-list.component.html',
  styleUrls: ['./posts-list.component.css']
})
export class PostsListComponent implements OnInit {
  posts: Post[] = [];

  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts(): void {
    this.postService.getAllPosts()
      .subscribe(posts => this.posts = posts);
  }
}
