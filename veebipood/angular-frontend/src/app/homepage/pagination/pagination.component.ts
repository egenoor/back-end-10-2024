import { Component, EventEmitter, Input, Output } from '@angular/core'

@Component({
  selector: 'app-pagination',
  standalone: true,
  imports: [],
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss'
})
export class PaginationComponent {
  @Input() totalPages = 0;
  @Output() pageChangeFetch: EventEmitter<number> = new EventEmitter<number>();
  page = 0;
  totalElements = 0;

  previousPage() {
    this.page--;
    this.pageChangeFetch.emit(this.page);
  }

  nextPage() {
    this.page++;
    this.pageChangeFetch.emit(this.page);
  }
}
