# networkprinter

imprime por ip

## Install

```bash
npm install networkprinter
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`imprimir(...)`](#imprimir)
* [`imprimirWithJar(...)`](#imprimirwithjar)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### imprimir(...)

```typescript
imprimir(options: ImprimirOptions) => Promise<void>
```

| Param         | Type                                                        |
| ------------- | ----------------------------------------------------------- |
| **`options`** | <code><a href="#imprimiroptions">ImprimirOptions</a></code> |

--------------------


### imprimirWithJar(...)

```typescript
imprimirWithJar(options: ImprimirOptions) => Promise<void>
```

| Param         | Type                                                        |
| ------------- | ----------------------------------------------------------- |
| **`options`** | <code><a href="#imprimiroptions">ImprimirOptions</a></code> |

--------------------


### Interfaces


#### ImprimirOptions

| Prop                | Type                |
| ------------------- | ------------------- |
| **`ip`**            | <code>string</code> |
| **`puerto`**        | <code>number</code> |
| **`dataaimprimir`** | <code>string</code> |

</docgen-api>
