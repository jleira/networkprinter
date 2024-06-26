import { MainPrinter } from 'networkprinter';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    MainPrinter.echo({ value: inputValue })
}
