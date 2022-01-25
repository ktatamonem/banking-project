import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(
    private router: Router,
    private authenticationService :AuthenticationService,
    private formBuilder: FormBuilder,
    private toastr :ToastrService


  ) { 
    if (this.authenticationService.isAuthenticated()) {
      this.router.navigate(['/']);
     }
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
  });
  }
  get f() { return this.loginForm.controls; }


  onSubmit() {
    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.authenticationService.login(this.loginForm.value.username, this.loginForm.value.password).subscribe(data => {
      localStorage.setItem("token",data.token);
      localStorage.setItem("username",data.username); 
      this.router.navigate(['/']);
    },()=>{
      this.toastr.error("Wrong username or password , please check your credentials")
    })


  }

}
