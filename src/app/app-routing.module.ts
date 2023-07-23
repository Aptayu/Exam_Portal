import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { UserhomeComponent } from './pages/user/userhome/userhome.component';
import { UserGuard } from './services/guards/user.guard';
import { AdminhomeComponent } from './pages/admin/adminhome/adminhome.component';
import { AdminGuard } from './services/guards/admin.guard';
import { QuestionComponent } from './pages/admin/question/question.component';

const routes: Routes = [
  { path: 'signup', component: SignupComponent,pathMatch: 'full' },
  { path: 'login', component: LoginComponent,pathMatch: 'full' },
  { path: '', component: HomeComponent,pathMatch: 'full' },
  { path: 'userhome', component: UserhomeComponent,pathMatch: 'full', canActivate:[UserGuard] },
  { path: 'adminhome', component: AdminhomeComponent,pathMatch: 'full', canActivate:[AdminGuard] },
  { path: 'question', component: QuestionComponent, pathMatch: 'full', canActivate:[AdminGuard] },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
