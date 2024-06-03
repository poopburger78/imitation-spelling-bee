// ***copy and pasted*** from the internet, then edited to fit what i wanted
const Keyboard = {
    elements: {
        main: null,
        keysContainer: null,
        keys: [],
        capsKey: null,
    },
 
    properties: {
        value: "",
        capsLock: false,
        keyboardInputs: null,
        keyLayout: [
            "B",
            "D",
            "R",
            "A",
            "N",
            "O",
            "R",
            "delete",
            "enter"
        ],
    },
 
    init() {
        // create and setup main element
        this.elements.main =
            document.createElement("div");
        this.elements.main.classList
            .add("keyboard", "keyboard--hidden");
        document.body
            .appendChild(this.elements.main);
 
        // create and setup child container component
        this.elements.keysContainer =
            document.createElement("div");
        this.elements.keysContainer
            .classList.add("keyboard__keys");
        this.elements.main
            .appendChild(this.elements.keysContainer);
 
        // create and setup key elements
        this.elements.keysContainer
            .appendChild(this._createKeys());
        this.elements.keys =
            this.elements.keysContainer
                .querySelectorAll(".keyboard__key");
 
        // open keyboard for elements with .use-keyboard-input
        this.properties.keyboardInputs =
            document.querySelectorAll(
                ".use-keyboard-input"
            );
        this.properties
            .keyboardInputs
            .forEach((element) => {
                element.addEventListener("focus", () => {
                    this
                        .open(element.value, (currentValue) => {
                            element.value = currentValue;
                        });
                });
            });
    },
 
    _createIconHTML(icon_name) {
        return `<span class="material-icons">${icon_name}</span>`;
    },
 
    _createKeyBtn(iconName, class1, onclick, class2) {
        this.keyElement =
            document.createElement("button");
 
        // add common attributes and classes
        this.keyElement
            .setAttribute("type", "button");
        this.keyElement
            .classList.add("keyboard__key");
 
        // add specific listeners and classes
        this.keyElement
            .classList.add(class1, class2);
        this.keyElement.innerHTML =
            this._createIconHTML(iconName);
        this.keyElement
            .addEventListener("click", onclick);
    },
 
    _createKeys() {
        const fragment =
            document.createDocumentFragment();
 
        this.properties.keyLayout.forEach((key) => {
            const insertLineBreak =
                ["delete", "p", "enter", "?"].indexOf(key) !== -1;
 
            switch (key) {
                case "delete":
                    this._createKeyBtn(
                        "delete", "keyboard__key--wide",
                        () => {
                            this.properties.value =
                                this.properties.value.slice(0, -1);
                            this._updateValueInTarget();
                        });
                    break;
 
                case "enter":
                    this._createKeyBtn(
                        "enter", "keyboard__key--wide",
                        () => {
                            this.properties.value = "";
                            this._updateValueInTarget();
                        });
                    break;
 
                default:
                    this._createKeyBtn();
                    this.keyElement.textContent = key;

                    this.keyElement
                        .addEventListener(
                            "click",
                            () => {
                                this.properties.value += key;
                                this._updateValueInTarget();
                            });
                    break;
            }
 
            fragment.appendChild(this.keyElement);
 
            if (insertLineBreak) {
                fragment
                    .appendChild(document.createElement("br"));
            }
        });
        return fragment;
    },
 
    _updateValueInTarget() {
        this.properties
            .keyboardInputs
            .forEach((keyboard) => {
                keyboard.value =
                    this.properties.value;
            });
    },
};
 
window.addEventListener("DOMContentLoaded", function () {
    Keyboard.init();
});
