platform :ios, '14.0'

project 'YourApp.xcodeproj'

target 'YourApp' do
  use_frameworks!

  pod 'Alamofire', '~> 5.4'
  pod 'RealmSwift'

  target 'YourAppTests' do
    inherit! :search_paths
    # Añade dependencias específicas para los tests aquí
  end

  target 'YourAppUITests' do
    inherit! :search_paths
    # Añade dependencias específicas para los tests UI aquí
  end
end