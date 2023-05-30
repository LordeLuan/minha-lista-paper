import { Item } from '../models/Item';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ItemService {
  private readonly API = 'http://localhost:8080/itens';
  constructor(private http: HttpClient) {}

  listar(): Observable<Item[]> {
    return this.http.get<Item[]>(this.API);
  }
  buscar(id: number): Observable<Item> {
    return this.http.get<Item>(this.API + `/${id}`);
  }
  criar(item: Item): Observable<Item> {
    return this.http.post<Item>(this.API, item);
  }
  atualizar(item: Item): Observable<Item> {
    return this.http.put<Item>(this.API, item);
  }
  deletar(id: number): Observable<Item> {
    return this.http.delete<Item>(this.API + `/${id}`);
  }
}
