import { Component, ElementRef, QueryList, ViewChildren } from '@angular/core';

@Component({
  selector: 'app-list-items',
  templateUrl: './list-items.component.html',
  styleUrls: ['./list-items.component.css']
})
export class ListItemsComponent {
  items : any = [
    {
      "desc":"arroz",
    },
    {
      "desc":"feijão",
    }
  ]

  selectedItems : any = []

  addItem(){
    this.items.push({"desc":""})
  }

  removeItem(index:number){
    this.items.splice(index,1)
  }

  editItem(event:Event, index:number){
    this.items[index].desc = (<HTMLInputElement>event.target).value
  }

  addSelectedItem(desc: string, index:number){
    this.selectedItems.push({"desc":desc})
    this.removeItem(index)
  }

  removeSelectedItem(desc: string, index:number){
    this.items.push({"desc":desc})
    this.selectedItems.splice(index,1)
  }

  deleteSelectedItem(index:number){
    this.selectedItems.splice(index,1)
  }

  @ViewChildren('input') inputs: QueryList<ElementRef> | undefined;

  ngOnInit(){
    
  }

  ngAfterViewChecked(){
    this.inputs!.last.nativeElement.focus();
  }
}
