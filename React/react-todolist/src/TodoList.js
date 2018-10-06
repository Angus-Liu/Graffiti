import React from 'react';
import TodoItem from './TodoItem'
import Test from './Test'
import ConstObj from './ConstObj'

class TodoList extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      list: [],
      inputValue: ''
    }

    this.handleInputChange = this.handleInputChange.bind(this)
    this.handleBtnClick = this.handleBtnClick.bind(this)
    this.handleDelete = this.handleDelete.bind(this)

    console.log(ConstObj)
    ConstObj.name = '123'
    console.log(ConstObj)
  }

  handleInputChange(e) {
    this.setState({
      inputValue: e.target.value
    })
  }

  handleBtnClick() {
    this.setState({
      list: [...this.state.list, this.state.inputValue],
      inputValue: ''
    })
  }

  handleDelete(index) {
    const list = [...this.state.list]
    list.splice(index, 1)
    this.setState({list})
  }

  getTodoList() {
    return (
      this.state.list.map((item, index) => {
        return (
        <TodoItem key={index} content={item} index={index} handleDelete={this.handleDelete}/>
        )
      })
    )
  }
  
  render() {
    console.log('TodoList render')
    return (
      <div>
        <div>
          <input value={this.state.inputValue} onChange={this.handleInputChange}/>
          <button onClick={this.handleBtnClick}>add</button>
        </div>
        <ul>{this.getTodoList()}</ul>
        <Test/>
      </div> 
    );
  }
}

export default TodoList;
