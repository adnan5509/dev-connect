export interface AuthorityRequest {
    authority: string;
}

export interface UserRegisterRequest {
    username: string;
    password: string;
    email: string;
    bio: string;
    githubUrl: string;
    authorities: AuthorityRequest[];  // ✅ Match backend structure
}