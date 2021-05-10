import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ColaboradorCadastroRequest } from '../shared/model/colaborador-model';

@Injectable({
  providedIn: 'root'
})
export class ColaboradorService {
  private URL = 'https://unidac-api.herokuapp.com/colaboradores'

  constructor(
    private httpClient: HttpClient
  ) { }

  adicionar(colaborador: ColaboradorCadastroRequest): Promise<any> {
    return this.httpClient.post(this.URL, colaborador).toPromise();
  }

  atualizar(id: number, colaborador: ColaboradorCadastroRequest): Promise<any> {
    return this.httpClient.put(this.URL + '/' + id, colaborador).toPromise();
  }

  remover(id: number): Promise<any> {
    return this.httpClient.delete(this.URL + '/' + id).toPromise();
  }

  buscarPeloId(id: number): Promise<any> {
    return this.httpClient.get(this.URL + '/' + id).toPromise();
  }

  buscarTodos(): Promise<any> {
    return this.httpClient.get(this.URL).toPromise();
  }
  
}
