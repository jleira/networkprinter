import { WebPlugin } from '@capacitor/core';

import type { ImprimirOptions, MainPrinterPlugin } from './definitions';

export class MainPrinterWeb extends WebPlugin implements MainPrinterPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
  async imprimir(options: ImprimirOptions): Promise<void> {
    console.log(options.dataaimprimir)
    window.print(); 
  }
  async imprimirWithJar(options: ImprimirOptions): Promise<void> {
    console.log(options.dataaimprimir);
    window.print(); 
  }

  async initPrint(ip: string): Promise<void> {
    console.log(ip)
  }

  
}
