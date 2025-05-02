
new Vue({
  el: "#todoapp",
  data: {
    list: [],
    inputValue: "",
  },
  methods: {
    remove: function (index) {
      this.list.splice(index, index + 1);
    },
    add: function () {
      if (this.inputValue != "") {
        this.list.push(this.inputValue);
        this.inputValue = "";
      }
      console.info(list)
    },
    clear: function () {
      this.list.forEach(() => {
        this.list.pop();
      });
      this.list.length = 0;
    }
  }
})
