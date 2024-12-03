import { Component, OnInit } from '@angular/core'
import { FormsModule } from '@angular/forms'
import { RouterLink } from '@angular/router'
import { OrderRow } from '../models/OrderRow'
import { Product } from '../models/Product'
import { NameShortenerPipe } from '../pipes/name-shortener.pipe'
import { ProductService } from '../services/product.service'
import { PaginationComponent } from './pagination/pagination.component'
import { SearchBarComponent } from './search-bar/search-bar.component'

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [FormsModule, RouterLink, FormsModule, PaginationComponent, SearchBarComponent, NameShortenerPipe],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent implements OnInit{
  products: Product[] = [];
  page = 0;
  totalPages = 0;
  totalElements = 0;
  nameLength = 5;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.fetchProducts(0);
  }

  fetchProducts(page: number) {
    this.productService.getProducts(page, 3).subscribe(response => {
      this.products = response.content;
      this.totalPages = response.totalPages;
      this.totalElements = response.totalElements;
    });
  }

  addToCart(productClicked: Product): void {
    const cartLS: OrderRow[] = JSON.parse(localStorage.getItem("cart") || "[]");
    const index = cartLS.findIndex(orderRow => orderRow.product.id === productClicked.id);
    if (index !== -1) {
      // suurendan kogust
      cartLS[index].pcs++;
    } else {
      // pushin > kui pole varasemalt ostukorvis
      cartLS.push({pcs: 1, product: productClicked});
    }
    localStorage.setItem("cart", JSON.stringify(cartLS));
  }

  fetchProductsByName(search: string) {
    this.productService.getProductsByName(search, 0, 3).subscribe(response => this.products = response.content)
  }
}

