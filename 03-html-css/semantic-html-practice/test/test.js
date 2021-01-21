(function() {

  'use strict';

  const fs = require('fs');
  const path = require('path');
  const chai = require('chai');
  const should = chai.should();
  const cheerio = require('cheerio');
  const request = require('request');
  const file = path.join(__dirname, '..', 'index.html');

  const $ = cheerio.load(fs.readFileSync(file));

  describe('index.html', () => {
    it('should have a header tag', () => {
      $('header').length.should.not.eql(0);
      $('header').length.should.be.at.least(1);
    });
    it('should have a nav tag', () => {
      $('nav').length.should.not.eql(0);
      $('nav').length.should.be.at.least(1);
    });
    it('should have a main tag', () => {
      $('main').length.should.not.eql(0);
      $('main').length.should.be.at.least(1);
    });
    it('should have a footer tag', () => {
      $('footer').length.should.not.eql(0);
      $('footer').length.should.be.at.least(1);
    });
    it('should have a ul tag', () => {
      $('ul').length.should.not.eql(0);
      $('ul').length.should.be.at.least(1);
    });
    it('should have a li tag', () => {
      $('li').length.should.not.eql(0);
      $('li').length.should.be.at.least(1);
    });
  });

}());
