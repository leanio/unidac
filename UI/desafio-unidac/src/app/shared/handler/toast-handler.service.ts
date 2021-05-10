import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class ToastHandlerService {

  constructor(
    private messageService: MessageService
  ) { }

  sucesso(descricao: string): void {
    this.messageService.add({severity: 'success', summary: 'Sucesso', detail: descricao})
  }

  erro(descricao: string): void {
    this.messageService.add({severity: 'error', summary: 'Erro', detail: descricao})
  }

  erroResponse(errorResponse: any): void {
    for(const error of errorResponse.error) {
      this.messageService.add({severity: 'error', summary: 'Erro', detail: error.descricao})
    }
  }

}
