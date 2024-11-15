import { Component } from '@angular/core'
import { SupplierService } from '../../services/supplier.service'

@Component({
  selector: 'app-supplier-escuela',
  standalone: true,
  imports: [],
  templateUrl: './supplier-escuela.component.html',
  styleUrl: './supplier-escuela.component.scss'
})
export class SupplierEscuelaComponent {
  supplierEscuelaProducts: any[] = [];

  constructor(private supplierService: SupplierService){}

  ngOnInit(): void {
    this.supplierService.getSupplierEscuelaProducts().subscribe(res => {
      this.supplierEscuelaProducts = res;
    })
  }
}
