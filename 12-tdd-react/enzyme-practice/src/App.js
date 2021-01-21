import React from 'react';

class App extends React.Component {
  state = {
    isAddRecipeFormDisplayed: false
  }

  toggleAddRecipeForm = () => {
    this.setState({isAddRecipeFormDisplayed: !this.state.isAddRecipeFormDisplayed})
  }

  submitRecipe = (event) => {
    event.preventDefault()
    var temp = 
      {
        name: this.state.newRecipeName, 
        instructions :this.state.newRecipeInstructions
      }
    
    if(Array.isArray(this.state.recipes)){
      this.setState({recipes: this.state.recipes.concat(temp)})
    } else {
      this.setState({recipes: [temp]})
    }
    this.setState({
      newRecipeName: "",
      newRecipeInstructions: ""
    })
  }

  handleChange = (e) => {
    this.setState({
      [e.target.name] : e.target.value,
    })
  }

  render(){
    const addNewRecipeForm = (
      <form id="recipe-form" onSubmit={this.submitRecipe}> 
        <label htmlFor="newRecipeName">Recipe name: </label>
        <input type="text" name="newRecipeName" onChange={this.handleChange} value={this.state.newRecipeName}/>
        <label htmlFor="newRecipeInstructions">Instructions:</label>
        <textarea name="newRecipeInstructions" placeholder="write recipe instructions here..." onChange={this.handleChange} value={this.state.newRecipeInstructions}/>
        <input type="submit" />
      </form>
    )
  
    const addRecipeButton = (
      <button id="add-recipe" onClick={this.toggleAddRecipeForm}>Add Recipe</button>
    )
  
    return (
      <div className="App">
        <h1 className="App-header">My Recipes</h1>
        {
          this.state.isAddRecipeFormDisplayed 
          ? addNewRecipeForm 
          : addRecipeButton
        }
        {
          Array.isArray(this.state.recipes)
          ? <ul> {this.state.recipes.map(recipe => <li key={recipe.name}>{recipe.name}</li>)} </ul>
          : <p>There are no recipes to list.</p>
        }
      </div>
    )
  }
}

export default App;