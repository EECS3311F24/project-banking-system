import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  private baseUrl = 'http://localhost:8080';


  constructor(private http: HttpClient) { }

  signup(user: { name: string; email: string; password: string }) {
    return this.http.post<{ accountNumber: string; balance: number }>(
      `${this.baseUrl}/addUser`,
      user
    );
  }

  login(email: string, password: string): Observable<{ 
    message: string; 
    name: string; 
    accountNumber: string; 
    balance: number 
  }> {
    const payload = { userId: email, password: password };
    return this.http.post<{ 
      message: string; 
      name: string; 
      accountNumber: string; 
      balance: number 
    }>(`${this.baseUrl}/login`, payload);
  }

  getAccountDetails(accountNumber: string): Observable<{ accountNumber: string; balance: number }> {
    return this.http.get<{ accountNumber: string; balance: number }>(
      `${this.baseUrl}/account/balance/${accountNumber}`
    );
  }

  deposit(accountNumber: string, amount: number): Observable<string> {
    const payload = { accountNumber, amount };
    return this.http.post<string>(`${this.baseUrl}/deposit`, payload);
  }

  withdraw(accountNumber: string, amount: number): Observable<any> {
    const payload = { accountNumber, amount };
    return this.http.post(`${this.baseUrl}/withdraw`, payload);
  }

  transferFunds(transferData: { fromEmail: string; toEmail: string; amount: number }) {
    return this.http.post(`${this.baseUrl}/transfer`, transferData, { responseType: 'text' });
  }
  getRecentTransaction(accountNumber: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/recentTransaction/${accountNumber}`);
  }

  getTransactionHistory(accountNumber: string) {
    return this.http.get<any[]>(`${this.baseUrl}/transactionHistory/${accountNumber}`);
  }
  
  

}