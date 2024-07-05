export interface MainPrinterPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  imprimir(options: ImprimirOptions): Promise<void>;
  imprimirWithJar(options: ImprimirOptions): Promise<void>;
}

export interface ImprimirOptions {
  ip: string;
  puerto: number;
  dataaimprimir: string;
}