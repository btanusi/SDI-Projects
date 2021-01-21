import App from './App';
import React from 'react';
import { shallow } from 'enzyme';

test('toggleAddRecipeForm() modifies isAddRecipeFormDisplayed state value to toggle visibility of a form on the page ', () => {
  const wrapper = shallow(<App />);
  wrapper.instance().toggleAddRecipeForm();

  wrapper.update();
  expect(wrapper.state().isAddRecipeFormDisplayed).toBeTruthy();
  expect(wrapper.exists("#recipe-form")).toEqual(true);

  wrapper.instance().toggleAddRecipeForm();
  expect(wrapper.exists("#recipe-form")).toEqual(false);
  expect(wrapper.state().isAddRecipeFormDisplayed).toBeFalsy();
});

test('the Add Recipe button onClick calls the toggleAddRecipeForm method', () => {
  const wrapper = shallow(<App />)

  // jest.fn() is a placeholder function
  wrapper.instance().toggleAddRecipeForm = jest.fn();
  wrapper.instance().forceUpdate();

  const button = wrapper.find('#add-recipe');
  button.simulate('click');

  expect(wrapper.instance().toggleAddRecipeForm).toHaveBeenCalled();
})


test('submitRecipe() modifies the recipes value in state', () => {
  const wrapper = shallow(<App />);
  const recipeName = "Wings";
  const recipeInstructions = "1a. Lemmon pepper on that. 1b. Buffalo";

  wrapper.setState({
    isAddRecipeFormDisplayed: true,
    newRecipeName: recipeName,
    newRecipeInstructions: recipeInstructions,
  });

  const submittedRecipe = { name: recipeName, instructions: recipeInstructions }

  const mockPreventDefault = jest.fn();

  wrapper.find('#recipe-form').simulate('submit', {
    mockPreventDefault: mockPreventDefault,
  })

  expect(mockPreventDefault).toHaveBeenCalled();
  expect(wrapper.state().recipes).toEqual([submittedRecipe])
})