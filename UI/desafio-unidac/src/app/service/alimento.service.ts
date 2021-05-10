import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AlimentoService {
  private URL = 'https://unidac-api.herokuapp.com/alimentos'

  constructor(
    private httpClient: HttpClient
  ) { }

  buscarTodos(): Promise<any> {
    return this.httpClient.get(this.URL).toPromise();
  }

}
