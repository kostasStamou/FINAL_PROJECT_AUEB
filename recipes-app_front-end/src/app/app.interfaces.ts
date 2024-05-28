export interface Recipe {
    recipeId: number;
    title: string;
    ingredients: string;
    description: string;
    duration: number;
  }

  export interface Post {
    postId: number;
    name: string;
    message: string;
  }

  export interface UserCredentials {
    email: string;
    password: string;
  }

  export interface Customer {
    customerId: number;
    firstname: string;
    lastname: string;
    email: string;
    password: string;
  }

  export interface Role {
    role: string;
  }

  export interface MenuItem {
    text: string;
    link: string;
  }