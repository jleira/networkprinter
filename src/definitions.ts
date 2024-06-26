export interface MainPrinterPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
