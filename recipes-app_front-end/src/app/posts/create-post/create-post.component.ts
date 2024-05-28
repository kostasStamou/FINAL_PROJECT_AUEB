import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostService } from 'src/app/posts/posts.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnDestroy {
  postForm: FormGroup;
  postStatus: number | undefined;
  private subscription: Subscription = new Subscription();

  constructor(
    private formBuilder: FormBuilder,
    private postService: PostService,
    private router: Router
  ) {
    this.postForm = this.formBuilder.group({
      name: ['', Validators.required],
      message: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.postForm.invalid) {
      return;
    }

    const newPost = {
      postId: 0,
      name: this.postForm.value.name,
      message: this.postForm.value.message
    };

    this.subscription.add(
      this.postService.createPost(newPost).subscribe(
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
      )
    );
  }

  navigateToPosts() {
    this.router.navigate(['/post']);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
