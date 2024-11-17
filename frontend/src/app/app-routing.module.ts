import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DepositComponent } from './deposit/deposit.component';
import { MainLayoutComponent } from './main-layout/main-layout.component';
import { AuthGuard } from './auth.guard';  
import { WithdrawComponent } from './withdraw/withdraw.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'dashboard',
    component: MainLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: '', component: DashboardComponent },
      { path: 'deposit', component: DepositComponent },
      { path: 'withdraw', component: WithdrawComponent }, 
    ],
  },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
