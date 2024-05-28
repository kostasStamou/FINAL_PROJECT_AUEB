import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Post } from 'src/app/app.interfaces';
import { PostService } from 'src/app/posts/posts.service';

@Component({
  selector: 'app-delete-post',
  templateUrl: './delete-post.component.html',
  styleUrls: ['./delete-post.component.css']
})
export class DeletePostComponent {
  posts: Post[] = [];
  postStatus: number | undefined;

  constructor(private postService: PostService,private router: Router) {}

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts(): void {
    this.postService.getAllPosts().subscribe(posts => (this.posts = posts));
  }

  deletePost(post: Post): void {
    this.postService.deletePost(post).subscribe(
      (response) => {
        console.log(response);
        if (response === null) {
          this.postStatus = 201;
        } else {
          this.postStatus = -1;
        }
      },
      () => {
        this.postStatus = -1;
      }
    );
  }

  navigateToPosts(): void {
    
    this.router.navigate(['/post']);
  }
}
