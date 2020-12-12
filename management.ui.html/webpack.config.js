const baseConfig = require('@eclipse-scout/cli/scripts/webpack-defaults');

module.exports = (env, args) => {
  args.resDirArray = ['src/main/resources/WebContent', 'node_modules/@eclipse-scout/core/res'];
  const config = baseConfig(env, args);

  config.entry = {
    'management': './src/main/js/management.js',
    'login': './src/main/js/login.js',
    'logout': './src/main/js/logout.js',
    'management-theme': './src/main/js/management-theme.less',
    'management-theme-dark': './src/main/js/management-theme-dark.less'
  };

  return config;
};
