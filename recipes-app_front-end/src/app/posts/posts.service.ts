import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from 'src/app/app.interfaces';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private apiUrl = 'http://localhost:8080/api/posts'; 

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`${this.apiUrl}/`);
  }

  createPost(post: Post): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/`, post);
  }

  deletePost(post: Post): Observable<any> {
    const postId = post.postId; 
  
    return this.http.delete<any>(`${this.apiUrl}/${postId}`);
  }
}
