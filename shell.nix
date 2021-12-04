{ pkgs ? import <nixpkgs> { } }:
pkgs.mkShell {
  packages = [ pkgs.openjdk ];
  JAVA_HOME = "${pkgs.openjdk.home}";
}
