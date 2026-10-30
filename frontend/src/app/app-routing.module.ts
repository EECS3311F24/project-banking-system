import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ETransferComponent } from './e-transfer/e-transfer.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DepositComponent } from './deposit/deposit.component';
import { MainLayoutComponent } from './main-layout/main-layout.component';
import { AuthGuard } from './auth.guard';  
import { WithdrawComponent } from './withdraw/withdraw.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { LoanManagementComponent } from './loan-management/loan-management.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'sign-up', component: SignUpComponent }, // Added signup route
  {
    path: 'dashboard',
    component: MainLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: '', component: DashboardComponent },
      { path: 'deposit', component: DepositComponent },
      { path: 'withdraw', component: WithdrawComponent }, 
      { path: 'e-transfer', component: ETransferComponent },
      { path: 'transactions', component: TransactionsComponent },
      { path: 'loan-management', component: LoanManagementComponent },
    ],
  },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
