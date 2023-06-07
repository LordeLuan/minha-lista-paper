import { Component, OnInit } from '@angular/core';
import { Item } from './../models/Item';
import { ItemService } from './../services/item.service';

@Component({
  selector: 'app-list-items',
  templateUrl: './list-items.component.html',
  styleUrls: ['./list-items.component.css'],
})
export class ListItemsComponent implements OnInit {
  selectedItems: Item[] = [];
  items: Item[] = [];

  item = new Item();

  add = false;

  constructor(private service: ItemService) {}

  ngOnInit(): void {
    this.listarItens();
  }

  listarItens() {
    this.service.listar().subscribe((resposta: Item[]) => {
      console.log(resposta);
      this.items.splice(0);
      this.selectedItems.splice(0);
      resposta.forEach((item) => {
        if (item.ativo) {
          this.items.push(item);
        } else {
          this.selectedItems.push(item);
        }
      });
    });
  }

  addItem() {
    this.add = !this.add;
  }

  cadastrarItem(){
    this.item.ativo = true;
    this.service.criar(this.item).subscribe(resposta =>{
      this.listarItens();
      this.item.nome = undefined;
    }, (ex)=>{
      alert('Algo deu errado ao realizar o cadastro do item!');
    })
  }

  deleteItem(id: any) {
    console.log(id);
    this.service.deletar(id).subscribe((resp)=>{
      console.log('Deletado!');
      this.listarItens();
    })
  }

  addSelectedItem(item:Item) {
    item.ativo = false;
    this.selectedItems.push(item);
    this.items.splice(this.items.indexOf(item),1);

    this.service.atualizar(item).subscribe(resposta =>{
      console.log('Atualizado!');
    });
  }
  
  removeSelectedItem(item:Item) {
    item.ativo = true;
    this.items.push(item);
    this.selectedItems.splice(this.selectedItems.indexOf(item), 1);

    this.service.atualizar(item).subscribe(resposta =>{
      console.log('Atualizado!');
    });
  }

  deleteSelectedItem(index: number) {
    this.selectedItems.splice(index, 1);
  }
}
