import { Component, Input } from '@angular/core';
import { MenuItem } from '../app.interfaces';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  @Input() items: MenuItem[] = [{text: "sample text", link: "sample link"}]
  @Input() dropdownLabel = 'Dropdown'
}
