import { Item } from './../models/Item';
import { ItemService } from './../services/item.service';
import { OnInit } from '@angular/core';
import { Component, ElementRef, QueryList, ViewChildren } from '@angular/core';

@Component({
  selector: 'app-list-items',
  templateUrl: './list-items.component.html',
  styleUrls: ['./list-items.component.css'],
})
export class ListItemsComponent implements OnInit {
  selectedItems: any = [];
  items: any = [];

  constructor(private service: ItemService) {}

  ngOnInit(): void {
    this.listarItens();
  }

  listarItens() {
    this.service.listar().subscribe((resposta: Item[]) => {
      console.log(resposta);
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
    this.items.push({ desc: '' });
  }

  removeItem(index: number) {
    this.items.splice(index, 1);
  }

  editItem(event: Event, index: number) {
    this.items[index].desc = (<HTMLInputElement>event.target).value;
  }

  addSelectedItem(desc: string, index: number) {
    this.selectedItems.push({ desc: desc });
    this.removeItem(index);
  }

  removeSelectedItem(desc: string, index: number) {
    this.items.push({ desc: desc });
    this.selectedItems.splice(index, 1);
  }

  deleteSelectedItem(index: number) {
    this.selectedItems.splice(index, 1);
  }

  @ViewChildren('input') inputs: QueryList<ElementRef> | undefined;

  // ngAfterViewChecked() {
  //   this.inputs!.last.nativeElement.focus();
  // }
}
