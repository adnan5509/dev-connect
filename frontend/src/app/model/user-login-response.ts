export interface Authority {
    authority: string;
  }
  
  export interface UserLoginResponse {
    username: string;
    email: string;
    bio: string;
    githubUrl: string;
    token: string;
    authorities: Authority[];
  }
  