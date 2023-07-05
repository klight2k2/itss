/**
 * 
 */
/**
 * @author 84971
 *
 */
module itss {
	requires javafx.graphics;
	requires java.desktop;
	requires java.net.http;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.controls;
	requires java.logging;
	requires java.sql;

	opens models;
	opens views;
	opens views.home;
	opens views.equipment;
	opens views.schedule;
	opens views.room;
	opens views.payborrow;
	opens views.report;
	opens views.login;
	opens controllers;
}