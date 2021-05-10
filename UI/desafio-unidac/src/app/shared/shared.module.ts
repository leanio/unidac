import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { ToastHandlerService } from './handler/toast-handler.service';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ToastModule
  ],
  exports:[
    ToastModule
  ],
  providers:[
    MessageService,
    ToastHandlerService
  ]
})
export class SharedModule { }
